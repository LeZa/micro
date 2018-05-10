package com.eureka.me.token.db;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.springframework.stereotype.Service;

public class RocksDBServiceDetail
        extends RocksDBService {

    private RocksDB rocksDB = null;

    private static final RocksDBServiceDetail rocksDBServiceDetail = new RocksDBServiceDetail();

    public static RocksDBServiceDetail getInstance() {
        rocksDBServiceDetail.initDB();
        return rocksDBServiceDetail;
    }

    @Override
    public void initDB() {
        try {
            if (this.rocksDB == null) {
                this.rocksDB = RocksDB.open("/home/centin/data/user");
            }
        } catch (RocksDBException exp) {
            exp.printStackTrace();
        }
    }

    public byte[] getString(String key) {
        try {
            return this.rocksDB.get(key.getBytes());
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setString(byte[] key, byte[] val) {
        try {
            this.rocksDB.put(key, val);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
    }


}
