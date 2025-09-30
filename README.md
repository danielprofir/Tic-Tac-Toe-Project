
## Running the Application

### Command Line Interface

The main command-line application is located in `app/src/main/Main.java`.

**To compile and run:**

```bash
# Compile all classes
javac -d out/production/tic-tac-toe-project lib/src/main/*.java app/src/main/*.java

# Run the CLI application
java -cp out/production/tic-tac-toe-project app.src.main.Main
```

The CLI provides an interactive game where you can:
- Choose player names for X and O
- Select player types (human or computer) with `[h/c]`
- For computer players, choose AI strategy (smart or random) with `[s/r]`
- Play a full game with automatic computer moves

**Note:** To run tests, you'll need to add JUnit to your classpath


## Documentation

### Complete Tutorial and Documentation

**All features and usage documentation is in the Jupyter notebook:**

**`notebooks/Project_Notebook.ipynb`**

This notebook includes:
-  Step-by-step explanation of all 8 requirements
-  Code examples for every feature
-  Live demonstrations of the library API
-  Human vs Human, Human vs Computer, and Computer vs Computer examples
-  Error handling and validation examples
-  Extensibility guide
-  Complete API reference

**To use the notebook:**

1. Install Jupyter and the IJava kernel (see below)
2. Navigate to the project directory
3. Run: `jupyter notebook`
4. Open `notebooks/Project_Notebook.ipynb`
5. Select the Java kernel
6. Run all cells to see the complete demonstration

### Java Kernel Setup (IJava)

If you have trouble running the Java kernel in Jupyter, please refer to the official IJava documentation:

**[IJava - A Jupyter kernel for Java](https://github.com/SpencerPark/IJava)**

**Quick setup:**
1. Download the latest IJava release from: https://github.com/SpencerPark/IJava/releases
2. Extract the archive
3. Run the installer: `python install.py --sys-prefix`
4. Verify with: `jupyter kernelspec list` (should show `java`)

The notebook uses relative paths and will work from any location after cloning from GitHub.

