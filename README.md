# 2022秋新人任务

## 第一次

### 任务内容

#### 文件加密

将指定文件的内容使用凯撒密码，后移用户指定位数  
`-100<=offset<=100`  
**函数签名**

```Java
public class Task {
    public String encrypt(File file, int offset) {
        return null;
    }
}
```

#### 文件解密

将用户指定的内容进行暴力破解，
已知正确破解后内容中包含用户指定的字符串，
返回一个 `Pair<Integer,String>` 对象， `Integer` 为正确破解的偏移量，
负数代表左移，
正数代表右移，
 `String` 为正确解密的内容。  
**函数签名**

```Java
public class Task {
    public Pair<Integer, String> decrypt(File file, String keyword) {
        return new Pair<>(0, null);
    }
}
```

### 相关补充

1. 克隆任务仓库
2. 创建新分支，分支名为自己的名字拼音或简拼
3. 创建类TaskImpl实现抽象类Task
4. 运行main函数，通过后提交到任务仓库

### 参考资料

1. [凯撒密码——百度百科](https://baike.baidu.com/item/%E6%81%BA%E6%92%92%E5%AF%86%E7%A0%81/4905284)
