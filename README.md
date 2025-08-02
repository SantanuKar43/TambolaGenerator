This is a simple CLI application to generate a Tambola card.
The default generated card follows Housie or UK Bingo rules:
- The cards contain 3 rows and 9 columns. 
- Each row contains five numbers and four blank spaces randomly distributed along the row. 
- Numbers are apportioned by column (1–10, 11–20, 21–30, 31–40, 41–50, 51–60, 61–70, 71–80 and 81–90)

The generator algorithm does it in 2 passes:
- first marks cells randomly to be left empty
- fills in random numbers in a columnwise manner in the cells not marked empty 


![img.png](samplecard.png)

References:
https://en.wikipedia.org/wiki/Bingo_(British_version)
https://en.wikipedia.org/wiki/Bingo_card

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
Usage: tambola-gen [-hV] [--csv] [--pretty] [-c=<cols>] [-n=<count>]
                   [--numsPerRow=<numsPerRow>] [-r=<rows>]
This is a simple CLI application to generate a Tambola card.
  -c, --cols=<cols>   Number of columns
                        Default: 9
      --csv           CSV print
  -h, --help          Show this help message and exit.
  -n=<count>          Number of cards to print
                        Default: 1
      --numsPerRow=<numsPerRow>
                      Numbers per row
                        Default: 5
      --pretty        Pretty print
  -r, --rows=<rows>   Number of rows
                        Default: 3
  -V, --version       Print version information and exit.
  
```