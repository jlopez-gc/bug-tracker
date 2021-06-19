import axios, { AxiosResponse } from 'axios';
import { BugPayload } from '../models/BugPayload';
import { UpdateRequestPayload } from '../models/UpdateRequestPayload';
import CreationRequestPayload from '../models/CreationRequestPayload';

export const findAllBugs = async (): Promise<BugPayload[]> => {
    return axios.get('/bug').then((response: AxiosResponse<BugPayload[]>) => response.data);
};

export const findBugById = async (id: number): Promise<BugPayload> => {
    return axios
        .get(`/bug/${id}`)
        .then((response: AxiosResponse<BugPayload>) => BugPayload.fromServerData(response.data));
};

export const createBug = (bug: BugPayload) => {
    const creationRequest: CreationRequestPayload = {
        name: bug.name,
        description: bug.description,
        status: bug.status ?? { id: -1, name: '' },
    };
    return axios.post(`/bug/`, creationRequest);
};

export const updateBugById = (id: number, bug: BugPayload) => {
    const updateRequest: UpdateRequestPayload = {
        id: bug.id ?? -1,
        name: bug.name,
        description: bug.description,
        status: bug.status ?? { id: -1, name: '' },
    };
    return axios.put(`/bug/${id}`, updateRequest);
};
