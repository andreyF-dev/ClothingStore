package com.andreyjig.clothingstore.model.handler;

import com.andreyjig.clothingstore.model.Product;

public interface ProductDescription extends ErrorDownload{
    void setDownloadedProduct(Product product);
}
