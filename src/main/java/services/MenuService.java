package services;

import models.enums.FilePathDataEnum;
import models.enums.StepEnum;
import utils.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

public interface MenuService {

    Properties prop = PropertiesUtil.getProperties(FilePathDataEnum.PATH_DATA_PROP_MESSAGE);

    StringBuilder getListMenu();

    StepEnum processLogin() throws IOException;

    void startMenu() throws IOException;



}
