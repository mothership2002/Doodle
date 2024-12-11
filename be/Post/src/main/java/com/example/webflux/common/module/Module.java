package com.example.webflux.common.module;

import lombok.extern.slf4j.Slf4j;

// TODO tamplate method가 맞는 방법인가?
@Slf4j
public abstract class Module {


    public void init() throws ClassNotFoundException {
        long start = System.nanoTime();
        construct();
        log.info("[{} finished in {} ms]", this.getClass().getSimpleName(), ((System.nanoTime() - start) / 1_000_000.0));
    }

    protected abstract void construct() throws ClassNotFoundException;

}