![Roughly Enough Characters logo, 参考智能ABC](./src/main/resources/assets/roughly-enough-characters/icon.png)

# Roughly Enough Characters

为 Minecraft 增加拼音搜索。[JustEnoughCharacters](https://github.com/Towdium/JustEnoughCharacters) 的 Fabric 版本。

大部分代码参考自 JECH，并使用和 JECH 同样的 [PinIn](https://github.com/Towdium/PinIn) 库实现匹配逻辑。

## 使用

在 [Modrinth](https://modrinth.com/mod/roughly-enough-characters) 上下载后，放入 mods 文件夹即可。

## Mod 兼容性

每个不同 Mod 的每个搜索框都需要单独适配。

目前以下搜索框已适配：

* 创造模式物品栏
* REI
* AE2 终端和接口终端
* Controlling
* ModMenu
* JEI (by @vfyjxf)
* Tom's Simple Storage Mod (by @vfyjxf)

目前我没有精力接受适配需求，主要适配自己玩的 Mod，但不反对任何人在 Issue 中提出适配需求。

## 开发

如果你想为某个 Mod 增加适配，请参考 [mixins](./src/main/java/com/oott123/rechars/mixins) 文件夹实现对应逻辑，然后在 [roughly-enough-characters.mixins.json](./src/main/resources/roughly-enough-characters.mixins.json) 中添加对应的 Mixin Class 定义即可。

我会定期接受适配的 Pull Request。
