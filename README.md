# Animania Mod

Hello and welcome to the Animania Mod git!

If you would like to learn more about the mod, check out the Wiki: http://www.animaniamod.net/

To Download the latest compiled code, visit CurseForge: https://minecraft.curseforge.com/projects/animania

To join our Discord, visit the following: https://discord.gg/7ne2Dqp

To use Animania-Base in your projects, include this in your build.gradle:
```
repositories {
	maven {
		url "https://maven.blamejared.com/"
	}
}

dependencies {
	deobfCompile "com.animania:animania-MCVERSION-base:MODVERSION" 
}
```

To use Animania Addons in your projects, additionally include this dependency:
```
deobfCompile "com.animania:animania-MCVERSION-ADDONNAME:MODVERSION" 
```
Make sure to replace `MCVERSION` and `MODVERSION` with the appropriate versions, and `ADDONNAME` with the name of the Addon you want to use.
