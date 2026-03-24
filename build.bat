@echo off
:: --- Multiverse Blackjack CLI Build Script ---

:: 1. Variables
set JDK_BIN="C:\Program Files\Java\jdk-23\bin"
set JAR_NAME=MultiverseBlackjack.jar

echo [1/4] Cleaning old files...
if exist %JAR_NAME% del %JAR_NAME%

echo [2/4] Compiling source code...
%JDK_BIN%\javac -d bin -sourcepath src src/core/Main.java

echo [3/4] Packaging executable JAR...
%JDK_BIN%\jar cvfm %JAR_NAME% manifest.txt -C bin .

echo [4/4] Launching game...
%JDK_BIN%\java -jar %JAR_NAME%

pause