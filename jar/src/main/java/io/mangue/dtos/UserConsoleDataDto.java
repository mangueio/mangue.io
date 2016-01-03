package io.mangue.dtos;

import io.mangue.models.App;
import io.mangue.models.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misael on 03/12/2015.
 */
public class UserConsoleDataDto implements Serializable{
    public User user;
    public List<App> apps;
}
