This is a simple CLI application to generate a Tambola card.
The default generated card follows Housie or UK Bingo rules:
- The cards contain 3 rows and 9 columns. 
- Each row contains five numbers and four blank spaces randomly distributed along the row. 
- Numbers are apportioned by column (1–9, 10–19, 20–29, 30–39, 40–49, 50–59, 60–69, 70–79 and 80–90)

![img.png](samplecard.png)

#### Prerequisites

- Java 21 or higher (for JAR version)
- GraalVM 21 or higher (for building native binary) - https://www.graalvm.org/downloads/

#### Building

```bash
cd TambolaGenerator
mvn clean package
```
Or if using Intellij IDEA,

go to **Build, Execution, Deployment > Build Tools > Maven > Runner**

and add **Environment variables**: `GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-jdk-21.0.6+8.1/Contents/Home/`

Then run maven goal: `mvn clean package`

```bash
#install
sudo cp ./target/tambola-gen /usr/local/bin/
```

#### Usage

```bash
$ tambola-gen

$ tambola-gen --help
Usage: tambola-gen [-hV] [--csv] [--pretty] [-c=<cols>]
                   [--numsPerRow=<numsPerRow>] [-r=<rows>]
This is a simple CLI application to generate a Tambola card.
  -c, --cols=<cols>   Number of columns, defaults to 9
      --csv           CSV print
  -h, --help          Show this help message and exit.
      --numsPerRow=<numsPerRow>
                      Numbers per row, defaults to 5
      --pretty        Pretty print
  -r, --rows=<rows>   Number of rows, defaults to 3
  -V, --version       Print version information and exit.

```