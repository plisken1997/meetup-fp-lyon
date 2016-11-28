const R = require('ramda');

const intro = 'Bienvenue au Meetup @ Link Value ! FP et apéro au menu ce soir ;-) ! Joie et amour à tous';

const cutOn = R.curry((rawContent, cut) => rawContent.split(cut));

const takeFirst = content => content.map(c => c.trim().split(" ")[0]);

const buildStr = R.curry((content, glue) => content.join(glue));


const build = buildStr(R.__, ' -> ');

const take = takeFirst;

const cut = cutOn(R.__, '!');

const run = R.pipe(
    cut,
    take,
    build
);

const resCompose = run(intro);

const res = buildStr(takeFirst(cutOn(intro, '!')), ' -> ');

console.log(resCompose, res);