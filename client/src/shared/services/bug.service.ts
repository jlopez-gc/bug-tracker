import axios, { AxiosResponse } from 'axios';
import { BugPayload } from '../models/BugPayload';
import BugUpdateRequestPayload from '../models/BugUpdateRequestPayload';
import BugCreationRequestPayload from '../models/BugCreationRequestPayload';

export const findAllBugs = async (): Promise<BugPayload[]> => {
    return axios.get('/bug').then((response: AxiosResponse<BugPayload[]>) => response.data);
};

export const findBugById = async (id: number): Promise<BugPayload> => {
    return axios
        .get(`/bug/${id}`)
        .then((response: AxiosResponse<BugPayload>) => BugPayload.fromServerData(response.data));
};

export const createBug = (bug: BugPayload) => {
    const creationRequest: BugCreationRequestPayload = BugCreationRequestPayload.from(bug);
    return axios.post(`/bug/`, creationRequest);
};

export const updateBugById = (id: number, bug: BugPayload) => {
    const updateRequest: BugUpdateRequestPayload = BugUpdateRequestPayload.from(bug);
    return axios.put(`/bug/${id}`, updateRequest);
};
