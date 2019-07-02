# IlluminaT_client
client app for IlluminaT project. Team: 縦割りプログラマ(Divided vertically Programmers)

## What is the IlluminaT?

IlluminaT is a project for developing controllable brooch with T-shirt. The brooch is consists from LED-Matrix. The  brooch  can display alphabet, katakana, hiragana and some signs, which can controll from Android.

This Repository manage only Android app source code. [HERE](https://github.com/NTSC-J/IlluminaT_controller) is a server program.

This project got 3rd prize in competition at an embedded system development class.

## Clinet screenshot
![gif](https://user-images.githubusercontent.com/7840108/60085789-e8d84980-9774-11e9-81fd-7f06d4920b3d.gif)


## DevelopEnvironment
- Android Studio 3.5 Beta 4

### Using Libs
- JetPack
- Retrofit
- OkHttp
- Moshi
- and some great libs

### Architecture
- MVVM with Navigation
  - view <-> viewmodel : Using LiveData to observe and notify some data
  - viewmodel <-> repository : Kotlin Coroutines
  - repository <-> Room, Api : Kotlin Corotuines
<img src="https://user-images.githubusercontent.com/7840108/60501322-cacf9380-9cf6-11e9-8112-e1cc0aa22299.png" width=500 />  

## Author
- [Kazuki Chigita](https://github.com/chigichan24)
