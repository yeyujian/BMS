
var note = {
    init: function(parent) {
        this.parent = parent;
        this.url = 'note://' + window.location.pathname;
        // initialize default notes
        if (!this.data())
            this.save(this.DEFAULT_NOTE);
    },
    data: function() {
        return localStorage.getItem(this.url);
    },
    save: function(text) {
        localStorage.setItem(this.url, text);
    },
    show: function() {
        this.parent.innerHTML = this.data();

        this.parent.onclick = function() {
            note.hide();
            editor.show(note.data());
            button.show();
        }
    },
    hide: function() {
        this.parent.onclick = null;
        this.parent.innerHTML = '';
    },
    DEFAULT_NOTE:
    '<p style="text-align:center"><img src="Images/welcome.png" /></p>'
    + '<h2 style="text-align:center">点击这里编辑笔记</h2>'
    + '<h3 style="text-align:center">Click here to edit notes</h3>'
};

var editor = {
    init: function(parent) {
        this.parent = parent;
    },
    data: function() {
        return this.value;
    },
    show: function(data) {
        this.ck = CKEDITOR.appendTo(this.parent, null, data);
        this.ck.on('instanceReady', function() {
            editor.ck.resize('100%', editor.parent.clientHeight);
        });
    },
    hide: function() {
        this.value = this.ck.getData();
        this.ck.destroy();
        delete this.ck;
    }
};

var button = {
    init: function() {
        this.save = document.getElementById('save');
        this.discard = document.getElementById('discard');

        this.save.onclick = function() {
            button.hide();
            editor.hide();
            note.save(editor.data());
            note.show();
        }
        this.discard.onclick = function() {
            button.hide();
            editor.hide();
            note.show();
        }
    },
    show: function() {
        this.save.style.display = "initial";
        this.discard.style.display = "initial";
    },
    hide: function() {
        this.save.style.display = "none";
        this.discard.style.display = "none";
    }
};

window.onload = function() {
    var content = document.getElementById('content');
    note.init(content);
    editor.init(content);
    button.init();

    note.show();
}
