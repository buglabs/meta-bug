require recipes-kernel/linux/linux.inc

KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "bug20"

PR = "r1"

SRCREV = "98c62bb9bc39f29c54e9dd1ed1ee4e64ac5669a9"


SRC_URI = "git://github.com/buglabs/bug20-2.6.35-linaro.git;branch=master;protocol=git"

S = "${WORKDIR}/git"

do_configure_prepend() {
	cp arch/arm/configs/omap3_buglabs_defconfig ${WORKDIR}/defconfig
}

do_configure_append() {
	sed -i -e "s/CONFIG_LOCALVERSION=\"\"/CONFIG_LOCALVERSION=\"-${PR}\"/g" ${S}/.config
}

# Make BMI header files available for JNI
do_install_append() {
	install -d ${D}${includedir}/linux/bmi/
	install -m 0644 ${S}/include/linux/bmi/*.h ${D}${includedir}/linux/bmi/

	install -d ${D}${sysconfdir}/
	echo "BUG Kernel Information" > ${D}${sysconfdir}/kernelinfo
	date >> ${D}${sysconfdir}/kernelinfo
	whoami >> ${D}${sysconfdir}/kernelinfo
	echo "git rev: ${SRCREV}" >> ${D}${sysconfdir}/kernelinfo
}

PACKAGES += "kernel-headers"
FILES_kernel-headers = "${includedir}/linux/bmi"
FILES_kernel-image += "${sysconfdir}/kernelinfo"

module_conf_g_file_storage = "options g_file_storage file=/dev/mmcblk1 removable=y"
module_autoload_g_file_storage = "g_file_storage"

module_autoload_bmi_li3m02cm = "bmi_camera \
iommu2 \
omap3-iommu \
omap3-isp \
omap34xxcam \
"
