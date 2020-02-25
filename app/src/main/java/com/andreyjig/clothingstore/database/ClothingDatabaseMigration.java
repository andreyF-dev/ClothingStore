package com.andreyjig.clothingstore.database;

import com.andreyjig.clothingstore.entity.ItemCard;
import com.andreyjig.clothingstore.entity.product.Color;
import com.andreyjig.clothingstore.entity.product.Image;
import com.andreyjig.clothingstore.entity.product.Manufacturer;
import com.andreyjig.clothingstore.entity.product.Material;
import com.andreyjig.clothingstore.entity.product.Size;
import com.andreyjig.clothingstore.entity.product.Variant;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class ClothingDatabaseMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion < 2){
            schema.get(Color.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(Image.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(Manufacturer.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(Material.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(Size.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(Variant.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
            schema.get(ItemCard.class.getSimpleName())
                    .addIndex("id")
                    .addPrimaryKey("id");
        }
    }


}
