package tsd;

import org.testng.annotations.Test;

import base.Browsers;
import bsn.Bsn_AddingToCart;

public class Tsd_AddingToCart extends Browsers {
    @Test
    public void addingToCart () {
        Bsn_AddingToCart addCart = new Bsn_AddingToCart(driver);
        addCart.Run();
    }
}
