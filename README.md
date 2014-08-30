BungeeCord plugin for FML builds that allow you to block mods from being used on your servers.

Drop it in your BungeeCord plugins folder, then run it. There should be two entries in the plugins/BlockMods/config.yml

`isWhitelisted`: set to `true` if you want to only allow specified mods, or `false` if you want to block specified mods. Defaults to `true`.
`modList`: The name of the mods to either allow or deny. This is a string list, and should be laid out as follows:

````
modList:
- mod1
- mod2
````

where `mod1` and `mod2` are mods to allow or deny, (as per your `isWhitelisted` setting). Case does not matter. "FML", "Forge" and "mcp" are automatically allowed through, due to them being required as part of the FML system!

Licensed under the AGPL v3.0. If you modify it and use it publically, you must share it!