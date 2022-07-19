export async function testNote() {
    setTimeout(function () {
		this.$waveui.notify("5 seconds have passed");
	}, 5000);
}