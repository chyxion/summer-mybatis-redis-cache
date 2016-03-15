# Summer MyBatis Redis Cache

## Usage

### Add Maven Dependency
```xml
    <dependency>
        <groupId>me.chyxion.summer</groupId>
        <artifactId>summer-mybatis-redis-cache</artifactId>
        <version>0.0.1-RELEASE</version>
    </dependency>
```

### MyBatis Settings Config
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" 
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
            <setting name="cacheEnabled" value="true" />
        </settings>
    </configuration>
```

### Mapper Config
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC
        "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
    <mapper namespace="me.chyxion.demo.mappers.UserMapper">
        <cache type="me.chyxion.summer.mybatis.cache.RedisCache" />
        ...
    </mapper>
```

## Contact

chyxion@163.com
