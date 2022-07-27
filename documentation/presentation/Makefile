
.PHONY: all presentation.pdf clean
all: presentation.pdf
presentation.pdf:
	TEXINPUTS=:. latexmk -pdf -silent presentation.tex
clean:
	rm -f *.aux *.synctex.gz *.bcf *.bbl *.blg *.fls *.fdb_latexmk *.log *.nav *.snm *.toc *.run.xml *.vrb *.pdf *~ presentation.out
