SyMAT Pad Viewer
================

Easily collaborate on SyMAT pads without installing SyMAT.

Download SyMAT
--------------
https://symatapp.com/

License
--------------
The Etherpad Lite library (org.etherpad_lite_client) is Apache licensed.
The original library can be found at https://github.com/nilsfr/java-etherpad-lite

The SyMAT code (com.netsyms) in this repository are BSD licensed (see source
 code headers), but you can use the Apache 2.0 license if you want to match 
with the library.

Modify
--------------
To make this project work with your own Etherpad instance, update 
/src/com/netsyms/symat/padview/PadUtils.java with your own PADS_URL, and put 
your Etherpad API key in the file `padkey` at the root of the `src` directory.