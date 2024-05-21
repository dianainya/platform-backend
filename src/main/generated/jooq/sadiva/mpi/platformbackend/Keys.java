/*
 * This file is generated by jOOQ.
 */
package jooq.sadiva.mpi.platformbackend;


import jooq.sadiva.mpi.platformbackend.tables.Dish;
import jooq.sadiva.mpi.platformbackend.tables.DishReceipt;
import jooq.sadiva.mpi.platformbackend.tables.PlatformRole;
import jooq.sadiva.mpi.platformbackend.tables.PlatformUser;
import jooq.sadiva.mpi.platformbackend.tables.Product;
import jooq.sadiva.mpi.platformbackend.tables.UserRole;
import jooq.sadiva.mpi.platformbackend.tables.records.DishReceiptRecord;
import jooq.sadiva.mpi.platformbackend.tables.records.DishRecord;
import jooq.sadiva.mpi.platformbackend.tables.records.PlatformRoleRecord;
import jooq.sadiva.mpi.platformbackend.tables.records.PlatformUserRecord;
import jooq.sadiva.mpi.platformbackend.tables.records.ProductRecord;
import jooq.sadiva.mpi.platformbackend.tables.records.UserRoleRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<DishRecord> DISH_NAME_KEY = Internal.createUniqueKey(Dish.DISH, DSL.name("dish_name_key"), new TableField[] { Dish.DISH.NAME }, true);
    public static final UniqueKey<DishRecord> DISH_PKEY = Internal.createUniqueKey(Dish.DISH, DSL.name("dish_pkey"), new TableField[] { Dish.DISH.ID }, true);
    public static final UniqueKey<DishReceiptRecord> DISH_RECEIPT_PKEY = Internal.createUniqueKey(DishReceipt.DISH_RECEIPT, DSL.name("dish_receipt_pkey"), new TableField[] { DishReceipt.DISH_RECEIPT.PRODUCT_ID, DishReceipt.DISH_RECEIPT.DISH_ID }, true);
    public static final UniqueKey<PlatformRoleRecord> PLATFORM_ROLE_PKEY = Internal.createUniqueKey(PlatformRole.PLATFORM_ROLE, DSL.name("platform_role_pkey"), new TableField[] { PlatformRole.PLATFORM_ROLE.NAME }, true);
    public static final UniqueKey<PlatformUserRecord> PLATFORM_USER_PKEY = Internal.createUniqueKey(PlatformUser.PLATFORM_USER, DSL.name("platform_user_pkey"), new TableField[] { PlatformUser.PLATFORM_USER.USER_ID }, true);
    public static final UniqueKey<PlatformUserRecord> PLATFORM_USER_USERNAME_KEY = Internal.createUniqueKey(PlatformUser.PLATFORM_USER, DSL.name("platform_user_username_key"), new TableField[] { PlatformUser.PLATFORM_USER.USERNAME }, true);
    public static final UniqueKey<ProductRecord> PRODUCT_NAME_KEY = Internal.createUniqueKey(Product.PRODUCT, DSL.name("product_name_key"), new TableField[] { Product.PRODUCT.NAME }, true);
    public static final UniqueKey<ProductRecord> PRODUCT_PKEY = Internal.createUniqueKey(Product.PRODUCT, DSL.name("product_pkey"), new TableField[] { Product.PRODUCT.ID }, true);
    public static final UniqueKey<UserRoleRecord> USER_ROLE_PKEY = Internal.createUniqueKey(UserRole.USER_ROLE, DSL.name("user_role_pkey"), new TableField[] { UserRole.USER_ROLE.USER_ID, UserRole.USER_ROLE.ROLE }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<DishReceiptRecord, DishRecord> DISH_RECEIPT__DISH_RECEIPT_DISH_ID_FKEY = Internal.createForeignKey(DishReceipt.DISH_RECEIPT, DSL.name("dish_receipt_dish_id_fkey"), new TableField[] { DishReceipt.DISH_RECEIPT.DISH_ID }, Keys.DISH_PKEY, new TableField[] { Dish.DISH.ID }, true);
    public static final ForeignKey<DishReceiptRecord, ProductRecord> DISH_RECEIPT__DISH_RECEIPT_PRODUCT_ID_FKEY = Internal.createForeignKey(DishReceipt.DISH_RECEIPT, DSL.name("dish_receipt_product_id_fkey"), new TableField[] { DishReceipt.DISH_RECEIPT.PRODUCT_ID }, Keys.PRODUCT_PKEY, new TableField[] { Product.PRODUCT.ID }, true);
    public static final ForeignKey<UserRoleRecord, PlatformRoleRecord> USER_ROLE__USER_ROLE_ROLE_FKEY = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_role_fkey"), new TableField[] { UserRole.USER_ROLE.ROLE }, Keys.PLATFORM_ROLE_PKEY, new TableField[] { PlatformRole.PLATFORM_ROLE.NAME }, true);
    public static final ForeignKey<UserRoleRecord, PlatformUserRecord> USER_ROLE__USER_ROLE_USER_ID_FKEY = Internal.createForeignKey(UserRole.USER_ROLE, DSL.name("user_role_user_id_fkey"), new TableField[] { UserRole.USER_ROLE.USER_ID }, Keys.PLATFORM_USER_PKEY, new TableField[] { PlatformUser.PLATFORM_USER.USER_ID }, true);
}
