export const mockedIssues = [
    {
        id: 524,
        name: 'Test Bug 1',
        description: 'This is a simple bug description',
        status: {
            id: 1,
            name: 'Pending',
        },
        createdAt: '2020-12-27T22:00:00',
        updatedAt: '2020-12-27T22:00:00',
    },
    {
        id: 525,
        name: 'Test Bug 2',
        description: 'This is a simple bug description',
        status: {
            id: 1,
            name: 'In Progress',
        },
        createdAt: '2020-12-27T22:00:00',
        updatedAt: '2020-12-27T22:00:00',
    },
];

export const validBug = {
    id: 1,
    name: 'Test Bug 1',
    description: 'This is a simple bug description',
    status: {
        id: 1,
        name: 'Pending',
    },
    createdAt: '2020-12-27T22:00:00',
    updatedAt: '2020-12-27T22:00:00',
};

export const emptyBug = {
    name: '',
    description: '',
    id: null,
    status: null,
    createdAt: '',
    updatedAt: '',
};
