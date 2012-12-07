dehoser
=======

Unpacker for the HoseDex2Jar APK Protection which packs the original file inside the dex header

## What is HoseDex2Jar? ##

  HoseDex2Jar is an attempt to create a service to obfuscate/mangle/mung APK files to prevent
  decompilation (maybe analysis?) of those files.
  The service is currently located at [http://www.decompilingandroid.com/hosedex2jar/]

The current method deployed by HoseDex2Jar is actually very similar (the same?) method described
in my BlackHat 2012 presentation. The original dex file is repackage into a zip/jar/apk (it's just
a compressed archive containing the dex file). Then this file is encrypted and packed into the
Dalvik header. This process is outlined in "DexEducation: Practicing Safe Dex" found here;
 [http://www.strazzere.com/papers/DexEducation-PracticingSafeDex.pdf]

## How does this work? ##
This is just a quick PoC unpacker - since the encryption methods are static. The interesting part
for me was just figuring out how the packing/unpacking was being done - so I didn't bother reversing
the actual crypto used. Since it was static across all binaries I used, I decided to take a "interesting"
way to do the crypto in a lazy way. Since the hoser is meant to break dex2jar, I thought it would be
funny to use dex2jar against itself! After running the crypto code through dex2jar, I simply imported
it into a java project and access the function directly. The rest of the code simply wraps this
function and properly reads the bytes needed for unpacked and decrypting.

Since I ended up being somewhat interesting in what was actualling going on in the (de)obfuscation layer,
I tore it apart and recoded it into the unpacker. This removes the external jar (created via dex2jar) as
a dependancy, though I left the function and the jar included into the project. I attempted to leave the
code as close to what it was compiled down to, including the silly function names. This might prove useful
for anyone who wants to reverse a newer version, or just follow along. I've also included the "dead" code,
which might be used to mangle some tools, or it could just be left of, or maybe it's just an attempt to
confused reversers. Either way, all the code is in there and commmented.

## Anything else? ##
A commented IDA Pro IDB file is in the examples directory. Dumped into this directory upon request :)

Tim Strazzere - diff@lookout.com - @timstrazz