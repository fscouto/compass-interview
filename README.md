# Contact Dedup

This project is part of the interview process for **Compass**.

## Instructions

You have been asked to take a list of contact information and identify which contacts are
potentially duplicates. You need to write a function that will do the following:

1. Identify which contacts are possible matches.
2. A score for each match that represents how accurate you believe the match is. This
   scoring is defined by you.
3. A contact might have multiple or no matches
4. All processing should be done in working memory (no database).

## Usage

1. Make sure you have Java installed.

2. Compile the code:

```bash
javac -d out/ src/main/java/compass/interview/*.java
```

3. Run the code:

```bash
java -cp out/ compass.interview.Main
```