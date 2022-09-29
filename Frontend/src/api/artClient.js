import BaseClass from "../util/baseClass";
import axios from 'axios'


/**
 * Client to call the ExampleService.
 */
export default class ArtClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getArt', 'addArt', 'getAllArt'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async getArt(artId, errorCallback) {
        try {
            const response = await this.client.get(`/art/${artId}`);
            return response.data;
        } catch (error) {
            this.handleError("getArt", error, errorCallback)
        }
    }

    async addArt(artId, name, artistName, errorCallback) {
        try {
            const response = await this.client.post(`/art`, {
                "artId": artId,
                "name": name,
                "artistName": artistName
            });
            return response.data;
        } catch (error) {
            this.handleError("addArt", error, errorCallback);
        }
    }

    async getAllArt(errorCallback){
        try {
            const response = await this.client.get(`/art/all`);
            return response.data;
        } catch (error) {
            this.handleError("getAllArt", error, errorCallback)
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
