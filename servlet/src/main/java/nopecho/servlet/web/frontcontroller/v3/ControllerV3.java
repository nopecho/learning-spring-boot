package nopecho.servlet.web.frontcontroller.v3.controller;

import nopecho.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
