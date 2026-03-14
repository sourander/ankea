# List all available commands by default
default:
	@just --list

# Compile and run the tests
test:
	mvn test

# Compile and launch the JavaFX application
run:
	mvn compile javafx:run

# Clean the build directory
clean:
	mvn clean

# Do a full clean, build, and package
build:
	mvn clean package

# Format the pom.xml after manual edits
format-pom:
    xq -i pom.xml

download-deps:
    #!/usr/bin/env -S uv run --script
    from pathlib import Path
    import requests

    print("Downloading dependencies...")
    dir = Path("jars")
    dokka_urls = [
        "https://repo1.maven.org/maven2/org/jetbrains/dokka/dokka-cli/2.1.0/dokka-cli-2.1.0.jar",
        "https://repo1.maven.org/maven2/org/jetbrains/dokka/gfm-plugin/2.1.0/gfm-plugin-2.1.0.jar",
        "https://repo1.maven.org/maven2/org/jetbrains/dokka/dokka-base/2.1.0/dokka-base-2.1.0.jar",
        "https://repo1.maven.org/maven2/org/jetbrains/dokka/analysis-kotlin-descriptors/2.1.0/analysis-kotlin-descriptors-2.1.0.jar",
    ]

    for url in dokka_urls:
        filename = url.split("/")[-1]
        filepath = dir / filename
        if not filepath.exists():
            print(f"Downloading {filename}...")
            response = requests.get(url)
            response.raise_for_status()
            dir.mkdir(exist_ok=True)
            with open(filepath, "wb") as f:
                f.write(response.content)
        else:
            print(f"{filename} already exists, skipping.")
    
# Java to Markdown using Dokka GFM plugin
gfm: download-deps
    @echo "Cleaning old docs"
    rm -r docs/reference/api
    @echo "Running Dokka CLI..."
    mkdir -p docs/reference/api
    java -jar jars/dokka-cli-2.1.0.jar dokka-configuration.json

docs: gfm
    uv run zensical serve --open