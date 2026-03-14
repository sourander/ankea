# History of the Creation of Ankea

## Install and Init

Commands that were run to set up the project were:

```bash
# Install SDKMAN!
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java 25 and Maven
sdk install java 25.0.2-tem
sdk install maven

# Create a new Maven project
cd ~/Code/$USER
mvn archetype:generate \
  -DgroupId=fi.jyu.ohj2.sourander.ankea \
  -DartifactId=ankea \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DinteractiveMode=false
cd ankea
```

## POM settings

For VS Code, install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) by Microsoft.

After this, the `pom.xml` was edited to add JavaFX dependencies and set the Java version to 25. I copy-pasted most of the JavaFX dependencies from the IDEA project. I suppose I could've used some `javafx-fxml-template` to do scaffolding, but... yeah.

## VS Code Settings

For this, I referred to [Java Development Setup, SDKMAN, Gradle, VS Code](https://mikyan.net/blog/java-development-setup/) guide by Mikyan.

## Justfile

I had some problems with the `Run` and `Debug` buttons in VS Codes code editor view. They appeared only in the preset `App.java` file, but not in the test file nor my actual `App.java` file.

CLI is king anyways, so I created a `Justfile` to run the app and tests with just `just run` and `just test` commands.

## Time spent

This took surprisingly long time, around 2 hours. I had to use Gemini 3.1 Pro to help me out in tough spots.