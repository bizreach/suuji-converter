Suuji (数字) converter [![Build Status](https://travis-ci.org/bizreach/suuji-converter.svg?branch=master)](https://travis-ci.org/bizreach/suuji-converter)
====

Convert suuji (数字) string to numeric value

## Setup

Add following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>jp.co.bizreach</groupId>
  <artifactId>suuji-converter</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Usage

You can convert a suuji strings using `jp.co.bizreach.suuji.SuujiConverter`:

```java
import jp.co.bizreach.suuji.SuujiConverter;

long value1 = SuujiConverter.convert("1万2千5百四十"); // => 12540
long value2 = SuujiConverter.convert("1万2000");       // => 12000
long value3 = SuujiConverter.convert("2千5百万");      // => 25000000
long value4 = SuujiConverter.convert("2千500万");      // => 25000000
long value5 = SuujiConverter.convert("2000万");        // => 20000000
long value6 = SuujiConverter.convert("2千万");         // => 20000000
```
