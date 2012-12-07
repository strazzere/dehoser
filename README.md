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

Tim Strazzere - diff@lookout.com - @timstrazz