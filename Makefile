.PHONY: compile run compilerun

compile:
	javac -cp . com/jesusurrutia/bomberchicken/*.java

run:
	java -Dcom.apple.mrj.application.apple.menu.about.name=BomberChicken com.jesusurrutia.bomberchicken.BomberChicken

compilerun:
	javac -cp . com/jesusurrutia/bomberchicken/*.java && java -Xdock:name="BomberChicken" com.jesusurrutia.bomberchicken.BomberChicken