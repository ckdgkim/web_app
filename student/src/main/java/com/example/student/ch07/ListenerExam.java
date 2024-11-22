package com.example.student.ch07;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {
  @Override
  public void attributeAdded(ServletContextAttributeEvent scae) {
    log.info("attributeAdded $$$$$$$$$$$$$: {}", scae);
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("contextInitialized !!!!!!!!!!!!!!: {}", sce);
  }

  @Override
  public void attributeAdded(HttpSessionBindingEvent se) {
    log.info("attributeAdded %%%%%%%%%%%%%: {} ", se );
  }

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    log.info("sessionCreated @@@@@@@@@@@@: {}", se );
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    log.info("contextDestroyed ###############: {}", sce);
  }
}
