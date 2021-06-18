// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import '@testing-library/jest-dom';
import { setupServer } from 'msw/node';
import { rest } from 'msw';

export const server = setupServer(
    rest.get('*/bug', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.json([
                {
                    id: 524,
                    name: 'Test Bug 1',
                    description: 'This is a simple bug description',
                    status: {
                        id: 1,
                        name: 'PENDING',
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
                        name: 'IN PROGRESS',
                    },
                    createdAt: '2020-12-27T22:00:00',
                    updatedAt: '2020-12-27T22:00:00',
                },
            ]),
        );
    }),
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());
