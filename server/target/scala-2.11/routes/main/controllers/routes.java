
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/elias/workspace/Weather Report - scalajs /server/conf/routes
// @DATE:Mon Aug 15 18:56:18 EEST 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseWeatherController WeatherController = new controllers.ReverseWeatherController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseWeatherController WeatherController = new controllers.javascript.ReverseWeatherController(RoutesPrefix.byNamePrefix());
  }

}
