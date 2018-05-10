package com.eureka.me.token.db;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class TokenRocksDBServiceDetail
        extends RocksDBService {


    private RocksDB rocksDB = null;

    private static final TokenRocksDBServiceDetail tokenRocksDBServiceDetail = new TokenRocksDBServiceDetail();

    public static TokenRocksDBServiceDetail getInstance() {
        tokenRocksDBServiceDetail.initDB();
        return tokenRocksDBServiceDetail;
    }

    @Override
    void initDB() {
        try {
            if (this.rocksDB == null) {
                this.rocksDB = RocksDB.open("/home/centin/data/token");
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
