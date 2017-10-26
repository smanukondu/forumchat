
bbcode_tags = new Array(
	'[b]', '[/b]',         // -- item 0 -- index 0
	'[i]', '[/i]',         // -- item 1 -- index 2
	'[u]', '[/u]',         // -- item 2 -- index 4
	'[s]', '[/s]',         // -- item 3 -- index 6
	'[br]', '',            // -- item 4 -- index 8
	'[url=]', '[/url]',    // -- item 5 -- index 10
	'[img]', '[/img]',     // -- item 6 -- index 12
	'[color]', '[/color]', // -- item 7 -- index 14
	'[:)]', '',            // -- item 8 -- index 16
	'[:(]', '');           // -- item 9 -- index 18

var openBBtags = new Array();

function tmIsTagOpen(eltag) {
	var tag = 0, i = 0;
	for (i = 0; i < openBBtags.length; i++) {
		if (openBBtags[i] == eltag) {
			tag++;
		}
	}
	if (tag > 0) {
		return true;
	} else {
		return false;
	}
}

function tmOpenTag(eltag) {
	if (bbcode_tags[eltag + 1] != '') {
		openBBtags[openBBtags.length] = eltag;
		// Add '*' in the button's text:
		eval('document.userPostForm.bbcode_' + eltag + '.value += "*"');
	}
}

function tmQuitTag(eltag) {
	var i = 0;
	for (i = 0; i < openBBtags.length; i++) {
		if (openBBtags[i] == eltag) {
			openBBtags.splice(i, 1);
			// Remove '*' from the button's text:
			buttext = eval('document.userPostForm.bbcode_' + eltag + '.value');
			eval('document.userPostForm.bbcode_' + eltag + '.value = "' + buttext.substr(0, (buttext.length - 1)) + '"');
		}
	}
}

function insertBBCode(eltag) {
	var txtarea = document.userPostForm.postContent;
	if (eltag == -1) {
		return;
	}
	// the function implement for MS IE Browser
	if (document.selection) {
		txtarea.focus();
		sel = document.selection.createRange();
		if (sel.text.length > 0) {
			if (eltag == 14) {
				sel.text = '[color=' + document.userPostForm.ddl_color_selector.value + ']' + sel.text + bbcode_tags[eltag + 1];
			} else {
				sel.text = bbcode_tags[eltag] + sel.text + bbcode_tags[eltag + 1];
			}
		} else {
			if (!tmIsTagOpen(eltag) || bbcode_tags[eltag + 1] == '') {
				if (eltag == 14) {
					sel.text = '[color=' + document.userPostForm.ddl_color_selector.value + ']';
				} else {
					sel.text = bbcode_tags[eltag];
				}
				tmOpenTag(eltag);
			} else {
				sel.text = bbcode_tags[eltag + 1];
				tmQuitTag(eltag);
			}
		}
		txtarea.focus();
	} // the function implement for Browser based on FireFox MOZILLAR or NETSCAPE
	else if (txtarea.selectionStart || txtarea.selectionStart == '0') {
		var startPos = txtarea.selectionStart;
		var endPos = txtarea.selectionEnd;
		var cursorPos = endPos;
		var scrollTop = txtarea.scrollTop;
		if (startPos != endPos) {
			if (eltag == 14) {
				var str_color_tag = '[color=' + document.userPostForm.ddl_color_selector.value + ']';
				txtarea.value = txtarea.value.substring(0, startPos) + (str_color_tag + txtarea.value.substring(startPos, endPos) + bbcode_tags[eltag + 1]) + txtarea.value.substring(endPos, txtarea.value.length);
				cursorPos += (str_color_tag.length + bbcode_tags[eltag + 1].length);
			} else {
				txtarea.value = txtarea.value.substring(0, startPos) + (bbcode_tags[eltag] + txtarea.value.substring(startPos, endPos) + bbcode_tags[eltag + 1]) + txtarea.value.substring(endPos, txtarea.value.length);
				cursorPos += (bbcode_tags[eltag].length + bbcode_tags[eltag + 1].length);
			}
		} else {
			if (!tmIsTagOpen(eltag) || bbcode_tags[eltag + 1] == '') {
				if (eltag == 14) {
					var str_color_tag = '[color=' + document.userPostForm.ddl_color_selector.value + ']';
					txtarea.value = txtarea.value.substring(0, startPos) + str_color_tag + txtarea.value.substring(endPos, txtarea.value.length);
					cursorPos = startPos + str_color_tag.length;
				} else {
					txtarea.value = txtarea.value.substring(0, startPos) + bbcode_tags[eltag] + txtarea.value.substring(endPos, txtarea.value.length);
					cursorPos = startPos + bbcode_tags[eltag].length;
				}
				tmOpenTag(eltag);
			} else {
				txtarea.value = txtarea.value.substring(0, startPos) + bbcode_tags[eltag + 1] + txtarea.value.substring(endPos, txtarea.value.length);
				cursorPos = startPos + bbcode_tags[eltag + 1].length;
				tmQuitTag(eltag);
			}
		}
		txtarea.focus();
		txtarea.selectionStart = cursorPos;
		txtarea.selectionEnd = cursorPos;
		txtarea.scrollTop = scrollTop;
	} // for other browser without above feature, then just implement as only inserting the open tag on the left
	else {
		if (!tmIsTagOpen(eltag) || bbcode_tags[eltag + 1] == '') {
			if (eltag == 14) {
				txtarea.value += ('[color=' + document.userPostForm.ddl_color_selector.value + ']');
			} else {
				txtarea.value += bbcode_tags[eltag];
			}
			tmOpenTag(eltag);
		} else {
			txtarea.value += bbcode_tags[eltag + 1];
			tmQuitTag(eltag);
		}
		txtarea.focus();
	}
}