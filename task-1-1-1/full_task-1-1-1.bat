SET PATH=%PATH%;kotlinc\bin;
call kotlinc src\main\kotlin\ru\nsu\fit\konstantinov\task_1_1_1\MyHeapSort.kt src\main\kotlin\Main.kt -include-runtime -d CheckHeapSort.jar
cd ..
call gradlew dokkaJavadoc
cd task-1-1-1
java -jar CheckHeapSort.jar
