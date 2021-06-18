import React from 'react';
import { render } from '@testing-library/react';
import { rest } from 'msw';
import { createHistory, createMemorySource, LocationProvider, Router } from '@reach/router';
import BugList from './components/BugList';
import { server } from './setupTests';
import userEvent from '@testing-library/user-event';

function renderWithRouter(
    component: JSX.Element,
    { route = '/', history = createHistory(createMemorySource(route)) } = {},
) {
    return {
        ...render(<LocationProvider history={history}>{component}</LocationProvider>),
        history,
    };
}

function renderWithRouterWrapper(
    ui: JSX.Element,
    { route = '/', history = createHistory(createMemorySource(route)) } = {},
) {
    return {
        ...render(
            <LocationProvider history={history}>
                <Router>{ui}</Router>
            </LocationProvider>,
        ),
        history,
    };
}

test('renders the bug id, name and status when found', async () => {
    const { findByText } = await renderWithRouterWrapper(<BugList path="/" />);
    const idElement = await findByText(/524/i);
    expect(idElement).toBeInTheDocument();
    const nameElement = await findByText(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();
    const statusElement = await findByText(/PENDING/i);
    expect(statusElement).toBeInTheDocument();
});

test('filters result using search input', async () => {
    const { getByTestId, findByText } = await renderWithRouterWrapper(<BugList path="/" />);

    const nameElement = await findByText(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();
    const searchInput = getByTestId(/search-input/i);
    userEvent.type(searchInput, 'Impossible');
    expect(nameElement).not.toBeInTheDocument();
    const noRecordMessage = await findByText(/No records found/i);
    expect(noRecordMessage).toBeInTheDocument();
});

test('renders error message on error', async () => {
    server.use(
        rest.get('*/bug', (req, res, ctx) => {
            return res(ctx.status(500));
        }),
    );
    const { findByText } = await renderWithRouterWrapper(<BugList path="/" />);
    const linkElement = await findByText(/An error occurred, please try again later/i);
    expect(linkElement).toBeInTheDocument();
});
