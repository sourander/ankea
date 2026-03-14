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