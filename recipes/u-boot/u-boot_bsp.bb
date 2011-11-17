require recipes-bsp/u-boot/u-boot.inc
PR = "r2"

FILESPATHPKG =. "u-boot-git:"

SRCREV_bug20 = "169a4c804dbaf11facb041b1333d394c6ceb8d68"

SRC_URI = "git://www.denx.de/git/u-boot.git;protocol=git \
           file://bug-uboot.patch \
           file://bug-video-setting.patch"

S = "${WORKDIR}/git"
