import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ArtClient from "../api/artClient";

class ArtPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetArt', 'onAddArt', 'renderArt'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers
     */
    async mount() {
        document.getElementById('create-comment-form').addEventListener('submit', this.onCreate);
        this.client = new CommentClient();

        this.dataStore.addChangeListener(this.renderComments)
        this.onGetComments()
    }

    /** Render Methods -----------------------------------------------------------------------------------------------*/

    async renderComments() {
        let resultArea = document.getElementById("result-info");

        const comments = this.dataStore.get("comments");

        let html = "<ul>";
        for (let comment of comments){
            html +=  '<li>' +
                `<h3>title: ` + comment.title + `</h3>` +
                `<h4>owner: ` + comment.owner + `</h4>` +
                `<p>content: ` + comment.content + `</p>` +
                '</li>';
        }
        html += "</ul>"

        if (comments){
            resultArea.innerHTML = html;
        } else {
            resultArea.innerHTML = "No Comments";
        }
    }

    /** Event Handlers -----------------------------------------------------------------------------------------------*/

    async onGetComments() {
        let result = await this.client.getAllComments(this.errorHandler);
        this.dataStore.set("comments", result);
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let owner = document.getElementById("create-comment-owner").value;
        let title = document.getElementById("create-comment-title").value;
        let content = document.getElementById("create-comment-content").value;

        const createdExample = await this.client.createComment(content, owner, title, this.errorHandler);

        if (createdExample) {
            this.showMessage("Posted a comment!")
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
        this.onGetComments()
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const commentPage = new CommentPage();
    commentPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
