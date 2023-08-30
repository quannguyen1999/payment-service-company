package services;

import models.enums.QuestionEnum;

import java.io.IOException;

public interface QuestionService {
     QuestionEnum processQuestion1(String input) throws IOException;
     QuestionEnum processQuestion2() throws IOException;
     QuestionEnum processQuestion3(String input) throws IOException;

     QuestionEnum processQuestion4() throws IOException;

     QuestionEnum processQuestion5(String input) throws IOException;

     QuestionEnum processQuestion6() throws IOException;

     QuestionEnum processQuestion7(String input) throws IOException;

     void processQuestion(QuestionEnum questionEnum) throws IOException;
}
