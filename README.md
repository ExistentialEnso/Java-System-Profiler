Java-System-Profiler
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
=====================
* Canonical Name
* Local IP Address
* Operating System
* Drives, including their space free and total space
* Primary screen's information (resolution, DPI, estimated size)

Example output:

```
{"name":"NameOnNetwork","ip_address":"192.168.1.10","drives":[{"name":"C:\\","display_name":"Windows SSD (C:)","total_space":120031539200,"free_space":4819816448,"is_writable":true},{"name":"D:\\","display_name":"Data Drive (D:)","total_space":889661685760,"free_space":875685748736,"is_writable":true}],"operating_system":"Windows 7"}
```
