package com.andreyjig.clothingstore.mvp.model.handler;


public interface DataHandler<T> extends ErrorDownload{

    void setDownloadedData(T data);

}
