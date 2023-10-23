module.exports = {
    ci: {
        collect: {
            url: ['https://www.us.elsevierhealth.com'],
            numberOfRuns: 3,
        },
        upload: {
            target: 'temporary-public-storage',
        },
    },
};
