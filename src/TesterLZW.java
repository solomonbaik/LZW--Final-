import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.*;
public class TesterLZW {
	public static void main(String [] args) throws IOException 
	{
		LZW john = new LZW ("lzw-file3.txt"); 
		
		//List<Integer> sampleInput = Arrays.asList(64, 256, 257, 258, 259, 260, 261, 261, 38, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 273, 262, 280, 281, 261, 10, 282, 285, 285, 38, 44, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 297, 286, 305, 283, 306, 308, 258, 288, 44, 42, 35, 314, 315, 316, 317, 318, 319, 320, 321, 322, 319, 38, 323, 326, 327, 328, 322, 311, 44, 309, 309, 284, 333, 308, 311, 313, 329, 340, 341, 330, 290, 47, 342, 346, 347, 314, 331, 336, 306, 335, 351, 286, 338, 348, 357, 328, 331, 291, 325, 358, 363, 321, 350, 354, 285, 353, 367, 281, 356, 340, 37, 32, 32, 362, 364, 324, 298, 377, 378, 318, 375, 381, 327, 366, 370, 280, 369, 388, 263, 289, 339, 359, 375, 375, 46, 382, 316, 360, 380, 399, 315, 38, 396, 375, 340, 387, 391, 260, 390, 410, 310, 393, 340, 406, 384, 403, 40, 294, 265, 295, 385, 363, 417, 376, 329, 409, 413, 257, 412, 430, 64, 372, 327, 374, 417, 37, 382, 401, 289, 269, 294, 42, 439, 378, 426, 408, 290, 433, 259, 432, 430, 435, 327, 426, 424, 346, 441, 44, 37, 272, 421, 447, 417, 458, 320, 429, 433, 453, 413, 455, 315, 437, 375, 394, 40, 417, 474, 406, 467, 379, 421, 275, 464, 328, 475, 32, 394, 488, 32, 404, 481, 483, 332, 451, 431, 497, 414, 312, 383, 426, 418, 494, 503, 418, 343, 484, 277, 37, 486, 322, 506, 427, 505, 506, 385, 469, 430, 471, 410, 473, 35, 514, 489, 437, 40, 405, 514, 482, 35, 420, 293, 278, 267, 42, 512, 319, 525, 461, 375, 47, 491, 466, 317, 519, 413, 521, 391, 523, 35, 46, 446, 316, 42, 32, 462, 557, 375, 554, 315, 557, 365, 509, 535, 267, 295, 561, 317, 553, 318, 478, 35, 405, 437, 569, 524, 577, 547, 410, 549, 388, 551, 321, 47, 492, 524, 574, 449, 293, 462, 566, 271, 294, 577, 323, 586, 314, 492, 531, 546, 450, 499, 64, 582, 370, 584, 321, 492, 374, 596, 468, 444, 593, 485, 534, 416, 35, 611, 590, 496, 499, 606, 367, 608, 318, 556, 288, 492, 560, 323, 460, 44, 615, 510, 538, 320, 586, 629, 524, 563, 386, 603, 623, 604, 256, 626, 316, 631, 396, 641, 467, 37, 47, 565, 635, 276, 537, 617, 320, 560, 529, 492, 46, 427, 326, 580, 391, 624, 354, 648, 318, 437, 394, 613, 534, 657, 615, 423, 330, 375, 40, 341, 668, 388, 670, 351, 672, 342, 460, 592, 678, 635, 294, 403, 349, 644, 497, 687, 336, 689, 428, 614, 693, 705, 637, 358, 685, 370, 700, 333, 702, 667, 423, 276, 37, 528, 270, 44, 375, 634, 266, 592, 654, 275, 511, 660, 363, 709, 367, 711, 309, 713, 321, 654, 656, 485, 721, 721, 720, 503, 289, 739, 32, 722, 275, 659, 292, 601, 643, 289, 646, 732, 337, 415, 386, 264, 569, 264, 634, 361, 679, 525, 540, 274, 720, 46, 422, 290, 42, 47, 772, 750, 508, 622, 699, 646, 434, 756, 714, 749, 655, 331, 38, 692, 679, 303, 762, 594, 396, 291, 540, 42, 774, 400, 698, 451, 754, 306, 713, 629, 406, 44, 420, 791, 566, 35, 292, 791, 741, 417, 46, 741, 811, 806, 266, 574, 760, 764, 506, 634, 703, 752, 604, 799, 305, 689, 692, 665, 525, 786, 766, 742, 809, 810, 743, 745, 745, 270, 665, 290, 742, 266, 44, 574, 815, 839, 38, 655, 461, 467, 730, 354, 826, 355, 780, 318, 360, 820, 845, 833, 721, 785, 830, 813, 44, 830, 821, 46, 813, 744, 746, 844, 765, 422, 38, 659, 802, 744, 839, 564, 824, 645, 646, 608, 265, 805, 860, 273, 815, 818, 844, 792, 821, 837, 32, 868, 863, 268, 847, 740, 743, 722, 442, 901, 822, 882, 776, 798, 778, 672, 288, 325, 901, 812, 832, 268, 850, 406, 866, 289, 870, 896, 872, 847, 851, 719, 426, 924, 662, 265, 537, 929, 37, 42, 445, 495, 753, 911, 857, 858, 290, 879, 397, 821, 535, 930, 866, 838, 791, 803, 811, 289, 533, 874, 929, 331, 917, 944, 803, 852, 797, 470, 940, 501, 379, 850, 290, 830, 312, 960, 803, 47, 267, 888, 820, 880, 950, 953, 950, 406, 794, 422, 396, 871, 720, 873, 443, 843, 962, 883, 777, 885, 941, 619, 748, 393, 718, 567, 978, 417, 971, 264, 808, 836, 920, 894, 867, 810, 739, 868, 988, 984, 740, 872, 269, 641, 1002, 360, 424, 853, 351, 855, 287, 941, 727, 749, 900, 745, 42, 478, 860, 867, 949, 1002, 872, 951, 767, 1001, 899, 264, 888, 442, 819, 978, 393, 898, 1002, 533, 943, 796, 992, 910, 994, 966, 846, 677, 463, 897, 930, 803, 1062, 812, 811, 1048, 1065, 981, 761, 1007, 1013, 266, 556, 985, 926, 815, 746, 444, 446, 1022, 336, 1024, 282, 356, 460, 983, 738, 1030, 926, 951, 1010, 1036, 1090, 1068, 406, 999, 959, 594, 906, 953, 719, 591, 1057, 909, 964, 1055, 296, 763, 1063, 981, 1015, 1037, 739, 814, 980, 961, 716, 1099, 1076, 841, 1098, 292, 748, 939, 1106, 1058, 593, 35, 936, 933, 838, 1089, 745, 1114, 1034, 1093, 1092, 816, 890, 803, 892, 267, 1005, 396, 271, 968, 293, 1124, 604, 460, 567, 894, 1041, 989, 540, 929, 514, 1076, 1076, 278, 815, 999, 1059, 848, 1101, 1147, 825, 965, 943, 790, 1116, 536, 915, 1109, 1136, 987, 1038, 417, 537, 898, 873, 420, 665, 813, 528, 488, 1069, 803, 1086, 984, 1071, 1151, 291, 1148, 499, 331, 974, 760, 842, 1174, 804, 659, 977, 880, 841, 979, 1177, 1174, 1164, 1072, 1203, 926, 270, 783, 277, 1193, 1167, 1106, 268, 1018, 1203, 1211, 1109, 1039, 1040, 803, 1162, 567, 813, 266, 976, 990, 705, 1198, 1104, 520, 1168, 270, 717, 1009, 1222, 880, 1115, 426, 871, 1221, 791, 1227, 1142, 1173, 396, 1097, 694, 44, 47, 1194, 497, 784, 1151, 742, 1002, 953, 1115, 837, 634, 397, 1250, 1246, 1188, 1000, 764, 1191, 1253, 634, 1256, 497, 850, 1192, 743, 1184, 960, 528, 1144, 1114, 839, 1044, 987, 769, 1268, 889, 819, 1003, 1231, 897, 1014, 1153, 1160, 442, 1275, 451, 1258, 38, 420, 1121, 746, 586, 904, 904, 657, 935, 922, 1137, 1157, 862, 1133, 1171, 593, 963, 1105, 778, 1169, 616, 1233, 566, 511, 1211, 1314, 920, 657, 943, 778, 1082, 1025, 1044, 1323, 890, 1267, 1060, 295, 1326, 1139, 1261, 1316, 273, 1050, 778, 605, 1346, 256, 1027, 1335, 1270, 1289, 1260, 1203, 398, 1163, 1244, 842, 1248, 1165, 38, 1346, 1332, 286, 290, 1141, 1351, 760, 1048, 1268, 1337, 1093, 804, 269, 1073, 1019, 265, 1252, 900, 1298, 1363, 1348, 647, 1321, 1368, 1032, 897, 1353, 1110, 1271, 960, 1152, 1343, 1297, 1274, 1348, 1364, 856, 289, 1367, 693, 290, 1293, 1232, 879, 1372, 1296, 914, 1370, 745, 1379, 616, 1396, 1397, 1383, 257, 1020, 1335, 903, 1264, 1405, 266, 1340, 1368, 1121, 1362, 1416, 670, 264, 410, 808, 1334, 1368, 1286, 946, 267, 1410, 957, 1295, 1209, 705, 1050, 1427, 607, 1444, 282, 284, 528, 1025, 1446, 625, 1385, 593, 1146, 1097, 1438, 1209, 741, 1325, 1390, 977, 1254, 265, 808, 787, 1381, 607, 1414, 280, 849, 284, 311, 574, 281, 1330, 583, 789, 1215, 1393, 1441, 972, 868, 892, 720, 462, 641, 837, 47, 1205, 1211, 1272, 272, 748, 1451, 688, 1318, 1417, 1432, 38, 335, 1050, 1469, 500, 1467, 1452, 1453, 1479, 1407, 466, 867, 844, 1151, 1198, 1198, 439, 972, 1133, 1412, 1121, 40, 550, 361, 262, 360, 1521, 335, 441, 1495, 441, 462, 1477, 1507, 1098, 1353, 1073, 634, 1197, 1108, 895, 947, 1476, 671, 1027, 1542, 1417, 534, 1347, 256, 1499, 1020, 1546, 1286, 1169, 1468, 1533, 1278, 1266, 1457, 1315, 922, 277, 1128, 1366, 288, 1438, 1374, 535, 1432, 1505, 712, 1418, 1542, 1572, 1254, 256, 353, 784, 384, 291, 818, 954, 1283, 291, 1449, 1496, 1556, 928, 1510, 961, 1492, 1557, 1462, 1122, 1413, 861, 688, 1569, 442, 651, 1044, 1502, 529, 784, 1576, 1504, 663, 376, 1146, 1599, 466, 1502, 1044, 733, 1602, 1336, 1028, 769, 1591, 1493, 1061, 929, 1538, 273, 1146, 1495, 1083, 441, 1282, 1510, 292, 974, 481, 1542, 412, 968, 1376, 396, 528, 943, 525, 1234, 1545, 1333, 1587, 808, 1360, 760, 1329, 1076, 481, 1618, 273, 997, 1430, 827, 677, 1641, 943, 1387, 396, 748, 42, 452, 392, 1640, 764, 331, 983, 503, 1604, 755, 1584, 1425, 1165, 692, 1100, 276, 1028, 1672, 1265, 457, 877, 1666, 1658, 1643, 1548, 263, 1050, 1632, 1670, 1538, 715, 682, 1626, 827, 401, 1623, 1674, 1361, 463, 749, 671, 1603, 633, 265, 1690, 457, 1662, 1695, 582, 1302, 291, 558, 1579, 905, 466, 401, 1695, 1571, 293, 1698, 1699, 1701, 291, 794, 522, 1028, 812, 1578, 418, 291, 974, 1447, 337, 997, 741, 1528, 1607, 384, 1195, 633, 1519, 1649, 293, 983, 1257, 1682, 534, 902, 393, 1719, 411, 1703, 426, 528, 1736, 851, 1416, 1642, 298, 345, 786, 532, 302, 265, 1383, 785, 1745, 1302, 1682, 1023, 671, 1144, 1438, 768, 1612, 722, 1416, 647, 1378, 1757, 297, 1162, 1779, 434, 1628, 1517, 457, 1772, 701, 985, 663, 1739, 426, 462, 677, 1785, 1798, 337, 1528, 544, 517, 264, 1144, 1081, 1791, 476, 1794, 758, 820, 1639, 1725, 1752, 1799, 1767, 1736, 849, 860, 384, 384, 38, 768, 1793, 333, 711, 1804, 530, 1144, 1820, 1337, 1760, 1531, 1815, 1346, 1768, 361, 1802, 1328, 481, 1829, 376, 1771, 308, 711, 749, 1637, 651, 406, 574, 1829, 1220, 1670, 1612, 1833, 1834, 583, 1544, 1374, 977, 1607, 1794, 957, 292, 334, 688, 441, 974, 1660, 417, 478, 768, 1144, 1862, 1611, 1797, 1856, 287, 1787, 1268, 265, 525, 528, 1609, 1758, 1825, 550, 1760, 442, 1869, 1840, 542, 1820, 764, 462, 1782, 592, 1799, 758, 614, 1838, 457, 557, 811, 1802, 1812, 1617, 686, 1741, 591, 1794, 1387, 374, 1603, 1221, 264, 1782, 292, 40, 917, 1470, 267, 1239, 298, 1669, 977, 1714, 1850, 376, 1714, 481, 1858, 1778, 1054, 1835, 1609, 1930, 466, 1651, 1842, 1268, 553, 1706, 1917, 303, 1538, 37, 1559, 682, 1370, 785, 407, 1914, 466, 1858, 1733, 1382, 1798, 1683, 361, 1847, 839, 1890, 1707, 1940, 1389, 1966, 1915, 1809, 1822, 426, 794, 1594, 1798, 1398, 701, 401, 794, 1559, 325, 1891, 1827, 1947, 1221, 1714, 1874, 514, 611, 1986, 1841, 1244, 1738, 1856, 1975, 1445, 534, 586, 1682, 1861, 396, 1997, 879, 1827, 1902, 376, 405, 1681, 1603, 524, 1183, 1881, 32, 1629, 1890, 426, 974, 1876, 1877, 1605, 2018, 1524, 1867, 1951, 1926, 1809, 1670, 479, 1603, 628, 1893, 913, 2024, 1757, 1655, 2020, 1664, 2035, 1878, 295, 1197, 1852, 1967, 1389, 818, 1884, 1334, 2037, 281, 1994, 1779, 1004, 302, 1762, 593, 439, 2045, 295, 718, 2047, 1865, 2059, 472, 723, 40, 1944, 301, 1031, 786, 2061, 413, 0, 0, 0, 0);
		long start = System.currentTimeMillis(); //
		
		String compressed = john.compress();
		
		long end = System.currentTimeMillis();
		long time = end - start;
		
		for(int n: john.compressed) {
			john.derp.add((Integer)n);
		}
	
		//System.out.println(john.decompressFromByteFile("output.byte")); EXTRA CREDIT
		
		//Note: this throws an error because the decoded int string doesn't seem to be correct
				
		//Proof that my decompression works:
		
		start = System.currentTimeMillis();
		//System.out.println(john.decompressFromInput(john.derp));
		end = System.currentTimeMillis();
		time += end - start;
		
		System.out.println("My code took " + time + " milliseconds to run.");		
	   
	}
}
