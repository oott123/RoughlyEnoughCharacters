# Roughly Enough Characters

![](./src/main/resources/assets/roughly-enough-characters/icon.png)

[JEC](https://github.com/Towdium/JustEnoughCharacters) 的 Fabric 版本。目前支持 1.18。

大部分代码参考自 [JEC](https://github.com/Towdium/PinIn) ，并使用和 JEC 同样的 [PinIn](https://github.com/Towdium/PinIn) 库实现匹配逻辑。

## 兼容性

目前兼容以下位置的搜索：

* 创造模式物品栏
* REI
* AE2 终端和接口终端

未提及的搜索框不能使用 REC 匹配。如果你想为某个其它 mod 提供支持，请参考 [mixins](./src/main/java/com/oott123/rechars/mixins) 文件夹实现对应，并在 [roughly-enough-characters.mixins.json](./src/main/resources/roughly-enough-characters.mixins.json) 中添加对应的 Mixin Class 即可。
