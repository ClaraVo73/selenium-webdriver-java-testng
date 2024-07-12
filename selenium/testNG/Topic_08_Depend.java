package testNG;

import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_08_Depend {
    @Test
    public void Product_01_Create_Product(){
        Assert.assertTrue(false);
    }
    @Test(dependsOnMethods = "Product_01_Create_Product")
    public void Product_02_ReadProduct(){
    }
    @Test
    public void Product_03_Update_Product(){
    }
    @Test
    public void Product_04_Delete_Product(){
    }

}
