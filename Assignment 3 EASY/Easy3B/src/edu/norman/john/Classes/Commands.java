package edu.norman.john.Classes;
//  {"set-date", "add-person", "add-certificate", "print-person",
//  "print-people", "add-security", "book-spot", "report-case", "quit"}

public enum Commands {
    SET_DATE("set-date"){
//        @Override
        public void setDate(){
            System.out.println("hello");
        }
    },
    ADD_PERSON("add-person");

    private final String stringCommand;

    Commands(String stringCommand){
        this.stringCommand = stringCommand;
    }
}
