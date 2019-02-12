package com.zhxh.core.web;


public interface ActionHandler<T> {
  T handleAction() throws Exception;
}
