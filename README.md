Java System Profiler
====================
**PLEASE NOTE:** This is a very functional but very early version of this project. This is lower on my priority list for 
personal coding projects, though I intend on greatly improving its functionality over time.

* Version: v0.0.2
* License: GPLv3
* Author: Thorne Melcher (GitHub: ExistentialEnso)

A JSP-powered tool for getting information about your computer. Currently, this only lets you get a JSON dump of useful
information about a computer, including its drives and their current space. This is mapped to the path "/overview.json".

When the Servlet first starts, it will always take longer to respond, as it needs to populate the data initially. After
this point, it will respond quicker by pulling things out of cache. If you want to ensure the data is up-to-the-second,
just add "refresh=true" as a parameter to the request (e.g. "/overview.json?refresh=true").

Information Available
---------------------
* Canonical Name
* IP Address
* Operating System (including architecture and version)
* Processor
* Drives (including free and total space)
* Primary Monitor (including resolution, DPI, estimated size)

Example output:
```
{"name":"JohnDoeLaptop",
 "ip_address":"192.168.1.10",
 "drives":[
  {"name":"C:\\","display_name":"Windows(C:)","total_space":120031539200,"free_space":14312461312,"is_writable":true},
  {"name":"D:\\","display_name":"Data (D:)","total_space":889661685760,"free_space":375685748736,"is_writable":true}],
 "operating_system":{"name":"Windows 7","version":"6.1","architecture":"x86-64"},
 "processor":{"architecture":"x86-64","virtual_cores":8},
 "primary_screen":{"dimensions":{"width":1920,"height":1080},"dpi":96,"estimated_size":22.825424421026653}}
 ```
