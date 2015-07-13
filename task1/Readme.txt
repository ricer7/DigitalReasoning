To run the three programs outlined in the Remote Programming document, the configuration.properties file must configured
as shown below:


inputFile=<path to nlp_data.txt file for task1>
outputFile=<path to XML output file required for all three tasks
nerFile=<path to NER.txt file for task 2>
inputFilesDir=<folder containing nlp_data files for task 3>

Assumptions:

1) A sentence is assumed to be bounded by a word starting with a capital letter on the left and either a period ".", 
   exclamation point "!", question mark "?" or quote in combination with a space and a capital letter  on the left.
   
2) The data in the NER.txt file is parsed so that two or more words appearing on a single line were treated as separate
   entities.
   
 Limitations:
 
    The biggest limitation of the implemented approach is that slows down significantly with the increase in data to be processed.
    This effect can be minimized by optimizing the data structures used in the tasks.